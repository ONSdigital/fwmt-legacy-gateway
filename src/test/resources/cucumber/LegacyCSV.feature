Feature: Legacy CSV Handling

  Scenario: We upload the LFS sample data
    Given the database is clean
    When the CSV 'sample_LFS_2018-05-01T19-09-54Z' is imported from sample 'sampleLFSExampleAllRows.csv' and uploaded to the 'sample' endpoint
    Then the database should contain 0 jobs
    And TM should be sent 0 new jobs

  Scenario: We upload the GFF sample data
    Given the database is clean
    When the CSV 'sample_GFF_2018-05-01T19-09-54Z' is imported from sample 'sampleGFFExampleAllRows.csv' and uploaded to the 'sample' endpoint
    Then the database should contain 0 jobs
    And TM should be sent 0 new jobs

  Scenario: We upload the staff data
    Given the database is clean
    When the CSV 'staff_2018-05-01T19-09-54Z' is imported from sample 'staffExampleAllRows.csv' and uploaded to the 'sample' endpoint
    Then the database should contain 0 users
