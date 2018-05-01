ALTER TABLE gateway.sample RENAME COLUMN authno TO auth_no;
ALTER TABLE gateway.sample RENAME COLUMN employeeno TO employee_no;
ALTER TABLE gateway.sample RENAME COLUMN addressline1 TO address_line_1;
ALTER TABLE gateway.sample RENAME COLUMN addressline2 TO address_line_2;
ALTER TABLE gateway.sample RENAME COLUMN addressline3 TO address_line_3;
ALTER TABLE gateway.sample RENAME COLUMN addressline4 TO address_line_4;
ALTER TABLE gateway.sample RENAME COLUMN addressno TO address_no;
ALTER TABLE gateway.sample RENAME COLUMN posttown TO post_town;
ALTER TABLE gateway.sample RENAME COLUMN osgridref TO os_grid_ref;
ALTER TABLE gateway.sample RENAME COLUMN primarykey TO primary_key;
ALTER TABLE gateway.sample RENAME COLUMN legacyjobid TO legacy_job_id;

ALTER TABLE gateway.staff RENAME COLUMN employeeNo TO employee_no;
ALTER TABLE gateway.staff RENAME COLUMN authNo TO auth_no;
ALTER TABLE gateway.staff RENAME COLUMN jobTitle TO job_title;
ALTER TABLE gateway.staff RENAME COLUMN userType TO user_type;

ALTER TABLE gateway.leavers RENAME COLUMN employeeNo TO employee_no;
ALTER TABLE gateway.leavers RENAME COLUMN authNo TO auth_no;
ALTER TABLE gateway.leavers RENAME COLUMN jobTitle TO job_title;
ALTER TABLE gateway.leavers RENAME COLUMN userType TO user_type;

ALTER TABLE gateway.jobs RENAME COLUMN legacyjobid TO legacy_job_id;
ALTER TABLE gateway.jobs RENAME COLUMN tmJobId TO tm_job_id;
ALTER TABLE gateway.jobs RENAME COLUMN initalTimestamp TO initial_time_stamp;
ALTER TABLE gateway.jobs RENAME COLUMN sentTimestamp TO sent_time_stamp;
ALTER TABLE gateway.jobs RENAME COLUMN processedTimestamp TO processed_time_stamp;
ALTER TABLE gateway.jobs RENAME COLUMN erroredTimestamp TO errored_time_stamp;

ALTER TABLE gateway.users RENAME COLUMN authNo TO auth_no;
ALTER TABLE gateway.users RENAME COLUMN tmUsername TO tm_username;
