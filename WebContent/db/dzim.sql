-- 찜 테이블
DROP SEQUENCE DZIM_SEQ;

DROP TABLE DZIM;

CREATE SEQUENCE DZIM_SEQ MAXVALUE 99999 NOCACHE NOCYCLE;

CREATE TABLE DZIM(
    ZNO         NUMBER(6) PRIMARY KEY,
    MID         REFERENCES MEMBER(MID),
    DNUM        REFERENCES DOG(DNUM),
    ZDATE       DATE DEFAULT SYSDATE NOT NULL
);

SELECT * FROM DZIM;


-- 찜 하기
INSERT INTO DZIM(ZNO, MID, DNUM ) VALUES (DZIM_SEQ.NEXTVAL,'aaa', '3');
INSERT INTO DZIM(ZNO, MID, DNUM ) VALUES (DZIM_SEQ.NEXTVAL,'aaa', '6');
INSERT INTO DZIM(ZNO, MID, DNUM ) VALUES (DZIM_SEQ.NEXTVAL,'aaa', '9');
SELECT * FROM DZIM;
SELECT * FROM DOG;
-- select count(*) from 찜 테이블 where mid = sessionid and wno='1';
-- 찜 눌렀는지 안눌렀는지 여부 
SELECT COUNT(*) FROM DZIM WHERE MID='aaa' AND DNUM=6;

-- 찜 취소 
DELETE FROM DZIM WHERE MID='aaa' AND DNUM=6;
ROLLBACK;
-- 내가 찜한 리스트 
SELECT DNUM, DNAME, DIMAGE1, DBREEDNAME FROM (SELECT * FROM DOG D, DBREED DB WHERE D.DBREEDNO = DB.DBREEDNO)
    WHERE DNUM IN (SELECT DNUM FROM DZIM WHERE MID='aaa') ;

