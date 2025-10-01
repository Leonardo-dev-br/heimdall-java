DECLARE
  v_table_count    NUMBER;
  v_seq_count      NUMBER;
  v_trg_count      NUMBER;
  v_fk_count       NUMBER;
  v_max_id         NUMBER;
  v_moto_table_cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_table_count FROM user_tables WHERE table_name = 'TB_HDL_HISTORICO_MOTO';
  IF v_table_count = 0 THEN
    EXECUTE IMMEDIATE q'[
      CREATE TABLE TB_HDL_HISTORICO_MOTO (
        ID_HISTORICO   NUMBER(19,0),
        ID_MOTO        NUMBER(19,0) NOT NULL,
        DATA_ATIVIDADE DATE,
        ATIVIDADE      VARCHAR2(100)
      )
    ]';
    EXECUTE IMMEDIATE 'ALTER TABLE TB_HDL_HISTORICO_MOTO ADD CONSTRAINT PK_TB_HDL_HISTORICO_MOTO PRIMARY KEY (ID_HISTORICO)';
  END IF;

  SELECT COUNT(*) INTO v_seq_count FROM user_sequences WHERE sequence_name = 'SEQ_TB_HDL_HISTORICO_MOTO';
  IF v_seq_count = 0 THEN
    BEGIN
      SELECT NVL(MAX(ID_HISTORICO),0) INTO v_max_id FROM TB_HDL_HISTORICO_MOTO;
    EXCEPTION WHEN OTHERS THEN
      v_max_id := 0;
    END;
    EXECUTE IMMEDIATE 'CREATE SEQUENCE SEQ_TB_HDL_HISTORICO_MOTO START WITH ' || (v_max_id + 1) || ' INCREMENT BY 1 NOCACHE NOCYCLE';
  END IF;

  SELECT COUNT(*) INTO v_trg_count FROM user_triggers WHERE trigger_name = 'TRG_TB_HDL_HISTORICO_MOTO_BI';
  IF v_trg_count = 0 THEN
    EXECUTE IMMEDIATE q'[
      CREATE OR REPLACE TRIGGER TRG_TB_HDL_HISTORICO_MOTO_BI
      BEFORE INSERT ON TB_HDL_HISTORICO_MOTO
      FOR EACH ROW
      WHEN (new.ID_HISTORICO IS NULL)
      BEGIN
        SELECT SEQ_TB_HDL_HISTORICO_MOTO.NEXTVAL INTO :new.ID_HISTORICO FROM dual;
      END;
    ]';
  END IF;

  SELECT COUNT(*) INTO v_moto_table_cnt FROM user_tables WHERE table_name = 'TB_HDL_MOTO';
  IF v_moto_table_cnt > 0 THEN
    SELECT COUNT(*) INTO v_fk_count
      FROM user_constraints
     WHERE constraint_name = 'FK_HISTORICO_MOTO_MOTO'
       AND table_name = 'TB_HDL_HISTORICO_MOTO';
    IF v_fk_count = 0 THEN
      EXECUTE IMMEDIATE q'[
        ALTER TABLE TB_HDL_HISTORICO_MOTO
          ADD CONSTRAINT FK_HISTORICO_MOTO_MOTO
          FOREIGN KEY (ID_MOTO) REFERENCES TB_HDL_MOTO(ID)
      ]';
    END IF;
  END IF;
END;
/
