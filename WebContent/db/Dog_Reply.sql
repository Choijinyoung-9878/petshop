-- 강아지 분양 게시판 댓글 테이블
DROP SEQUENCE DREPLY_SEQ;

CREATE SEQUENCE DREPLY_SEQ
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;

DROP TABLE DOG_REPLY;

CREATE TABLE DOG_REPLY(
    RNO NUMBER(6) PRIMARY KEY,  -- 댓글 번호
    DNUM REFERENCES DOG(DNUM) NOT NULL,
    MID REFERENCES MEMBER(MID) NOT NULL,
    REPLY_CONTENT VARCHAR2(1000) NOT NULL,
    RDATE DATE DEFAULT SYSDATE NOT NULL,
    RIP VARCHAR2(50) NOT NULL
);
SELECT * FROM DOG_REPLY;
-- 강아지 분양글 댓글쓰기
INSERT INTO DOG_REPLY (RNO, DNUM, MID, REPLY_CONTENT, RIP )
    VALUES (DREPLY_SEQ.NEXTVAL, 1, 'aaa', '이 강아지 너무 귀엽네요', '192.168.10.30');
INSERT INTO DOG_REPLY (RNO, DNUM, MID, REPLY_CONTENT, RIP)
    VALUES (DREPLY_SEQ.NEXTVAL, 3, 'aaa', '이 강아지 너무 사랑스럽네요', '192.168.10.30');

-- 강아지 분양글의 댓글 출력
SELECT D.* FROM(SELECT ROWNUM RN, A.* FROM
                (SELECT * FROM DOG_REPLY WHERE DNUM = 1 ORDER BY RDATE) A) D
    WHERE RN BETWEEN 1 AND 10 ;

-- 댓글 수정
UPDATE DOG_REPLY SET REPLY_CONTENT='이 강아지 제가 사랑스럽네요',
                        RIP ='192.147.12.30'
                            WHERE RNO=1;
                            
-- 댓글 삭제
DELETE DOG_REPLY WHERE RNO=3;

commit;