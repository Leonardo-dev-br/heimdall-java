DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = 'TB_HDL_USERS';
  IF v_count = 1 THEN
    SELECT COUNT(*) INTO v_count FROM TB_HDL_USERS WHERE EMAIL = 'admin@example.com';
    IF v_count = 0 THEN
      INSERT INTO TB_HDL_USERS (NAME, MIDDLENAME, BIRTHDAY, CPF, EMAIL, PASSWORD, CATEGORY_ID)
      VALUES ('Darrell', 'Lance Abott',  TO_DATE('2000-05-10', 'YYYY-MM-DD'), 66636523242, 'dimebag@hotmail.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5F9jwY1Vn.YBHGh0xy3YJZ6G1FDeW', 1);
      COMMIT;
    END IF;
  END IF;
END;
/
