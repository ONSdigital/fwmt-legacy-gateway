Feature: CSV Handling 
    Scenario: We upload the sample data 
        Given the CSV "LegacySampleData.csv" 
        When the csv is uploaded to "/sample" where form-data name is "fileSampleData"
        And ingested by CSV Ingester
        Then check the sample data was saved in the "sampledata" table 
        
    Scenario: We upload the allocation data 
        Given the CSV "LegacyAllocationData.csv" 
        When the csv is uploaded to "/allocation" where form-data name is "fileAllocation"
        And ingested by CSV Ingester
        Then check the allocated jobs are created in TM