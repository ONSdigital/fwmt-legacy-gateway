# FWMT Legacy Gateway API
This page documents the Fieldwork Management Tool (FWMT) Gateway API endpoints. Apart from the Service Information endpoint, all these endpoints are secured using HTTP basic authentication. All endpoints return an `HTTP 200 OK` status code except where noted otherwise.

## Content Type
Incoming requests must specify `multipart/form-data` as the value of the HTTP `Content-Type` header.
The resquest must contain a single form parameter `file` which contains a file of content type `text/csv`.

## Timestamps
Where timestamps are used they should be in UTC and formatted according to ISO 8601 with a date and time component separated by a letter "T" and a letter "Z" suffix to indicate Zulu time (UTC) i.e. *YYYY-MM-DDTHH:MM:SSZ*. For example, 2018-04-24T19:31:25Z.

## Error Handling
All endpoints that accept HTTP POST requests may return any one of the HTTP error status codes below:

* An `HTTP 400 Bad Request` is returned if the file name specified in the request does not match the file name format expected by the endpoint

* An `HTTP 415 Unsupported Media Type` is returned upon receipt of a non-CSV file

* An `HTTP 500 Internal Server Error` is returned if the data could not be persisted by the gateway for whatever reason

In addition to the HTTP error status code, a JSON error object will be returned in the HTTP response that provides additional information. See the individual endpoint documentation below for details of this error object.

## Service Information
* `GET /info` will return information about this service, collated from when it was last built.

### Example JSON Response
```json
{
    "name": "fwmt-legacy-gateway",
    "version": "1.0.0",
    "origin": "git@github.com:ONSdigital/fwmt-legacy-gateway.git",
    "commit": "e6891f890a4402e438918a5d261f0723f2838878",
    "branch": "master",
    "built": "2018-04-24T20:00:30Z"
}
```

## Upload Sample and Allocation Data
* `POST /samples` accepts a CSV file containing sample and allocation data as multi-part form data. The filename format must be *sample_&lt;survey_tla&gt;_&lt;timestamp&gt;.csv*

### Example JSON Response
```json
{
  "filename": "sample_GFF_2018-04-24T19:09:54Z.csv",
  "processedRows": 5000,
  "unprocessedRows": [
    {
      "row": 4,
      "message": "Row value is null"
    },
    {
      "row": 12,
      "message": "Row value is empty"
    }
  ]
}
```

The input filename is echoed back to the client upon a successful request, as is the number of processed rows within the CSV file that the gateway successfully persisted in its reception database table, prior to subsequent processing and transfer to the fieldwork management tool. A list of rows that could not be processed is also returned.

### Example JSON Error Response
```json
{
  "error": "Bad Request",
  "exception": "uk.gov.ons.fwmt.gateway.domain.InvalidFilenameException",
  "message": "Invalid filename",
  "path": "/samples",
  "status": 400,
  "timestamp": "2018-04-24T19:44:32Z"
}
```

## Upload Staff Data
* `POST /staff` accepts a CSV file containing staff data as multi-part form data. The filename format must be *staff_&lt;timestamp&gt;.csv*

### Example JSON Response
```json
{
  "filename": "staff_GFF_2018-04-24T19:09:54Z.csv",
  "processedRows": 750
}
```

The input filename is echoed back to the client upon a successful request, as is the number of processed rows within the CSV file that the gateway successfully persisted in its reception database table, prior to subsequent processing and transfer to the fieldwork management tool.

### Example JSON Error Response
```json
{
  "error": "Bad Request",
  "exception": "uk.gov.ons.fwmt.gateway.domain.InvalidFilenameException",
  "message": "Invalid filename",
  "path": "/staff",
  "status": 400,
  "timestamp": "2018-04-24T19:44:32Z"
}
```
