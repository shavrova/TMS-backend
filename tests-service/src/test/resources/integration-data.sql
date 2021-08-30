INSERT INTO features (`feature_id`, `feature_name`, `feature_description`, `class_name`)VALUES
('1', 'Login', 'This feature enables user to login into the system', 'LoginFeature'),
('2', 'Register', 'This feature enables user to register into the system', 'RegisterFeature'),
('3', 'Navigate', 'This feature enables user to navigate the system', 'NavigateFeature'),
('4', 'Process', 'This feature enables user to process data into the system', 'ProcessFeature');

INSERT INTO steps (`step_id`, `step_name`,`comment`,`method_name`, `created_at`, `updated_at`) VALUES
('1', 'Login user', 'comment', 'login', parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('2', 'Start processing', 'comment', 'startProcessing', parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('3', 'Request processing status', 'comment', 'requestStatus', parsedatetime('17-09-2012 18:12:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2021 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('4', 'Abort processing', 'comment', 'abortProcessing', parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('5', 'Register user', 'comment', 'register', parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));

INSERT INTO scenarios (`scenario_id`,`scenario_name`,`scenario_description`,`created_at`, `user_id`, `feature_id`) VALUES
('1', 'Login as operator', 'Verify user with role operator logged in successfully', parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS'), '1', '1'),
('2', 'Verify start processing', 'Verify processing start operation is successful', parsedatetime('17-09-2021 18:47:12.69', 'dd-MM-yyyy hh:mm:ss.SS'), '1', '4');

INSERT INTO scenario_step (`scenario_id`, `step_id`) VALUES
(1,1),
(1,3),
(2,2),
(2,3);
