-- 학과 TABLE
CREATE TABLE 학과
(
  학과코드 NUMBER(10) NOT NULL,         
  연락처 VARCHAR2(20) NOT NULL,        
  건물 NVARCHAR2(10) NOT NULL,             -- ex) 향파관 or A15
  방번호 NUMBER(4) NOT NULL,               -- ex) 409
  단과대학 NVARCHAR2(50) NOT NULL,         -- ex) 정보융합대학
  학과명 NVARCHAR2(50) NOT NULL,           -- ex) 컴퓨터공학부 
  PRIMARY KEY (학과코드),
  UNIQUE (학과명)
);

-- 학생 TABLE, (생년월일, 성별은 Derived attribute) 
CREATE TABLE 학생
(
  학번 NUMBER(9) NOT NULL,                -- ex) 201911608
  이름 NVARCHAR2(50) NOT NULL,            -- ex) 김지환
  주민등록번호 VARCHAR2(14) NOT NULL,      -- ex) 000529-3000000
  주소 NVARCHAR2(100) NOT NULL,           -- ex) 부산광역시 남구 ...
  연락처 VARCHAR2(20) NOT NULL,           -- ex) 010-0000-0000
  학년 NUMBER(1) NOT NULL,                -- ex) 3
  학과코드 NUMBER(10) NOT NULL,            
  PRIMARY KEY (학번),
  FOREIGN KEY (학과코드) REFERENCES 학과(학과코드),
  FOREIGN KEY (직원번호) REFERENCES 교수(직원번호),
  UNIQUE (주민등록번호)
);

-- Derived Attribute (성별, 생년월일)
ALTER TABLE 학생 ADD (
    -- GENERATED ALWAYS AS == 파생컬럼 생성
    성별 NVARCHAR2(4) GENERATED ALWAYS AS (
        CASE WHEN 
            SUBSTR(주민등록번호, 8, 1) IN ('1', '3') 
            THEN '남' 
            ELSE '여' 
        END
    ),
    생년월일 DATE GENERATED ALWAYS AS (TO_DATE(SUBSTR(주민등록번호, 1, 6), 'YYMMDD'))
);

-- 교수 TABLE
CREATE TABLE 교수
(
  직원번호 NUMBER(10) NOT NULL,
  이름 NVARCHAR2(50) NOT NULL,
  주민등록번호 VARCHAR2(14) NOT NULL,
  주소 NVARCHAR2(100) NOT NULL,
  연락처 VARCHAR2(20) NOT NULL,
  학과코드 NUMBER(10) NOT NULL,
  PRIMARY KEY (직원번호),
  FOREIGN KEY (학과코드) REFERENCES 학과(학과코드),
  UNIQUE (주민등록번호)
);

-- 과목 TABLE
CREATE TABLE 과목
(
  과목명 NVARCHAR2(50) NOT NULL,
  과목코드 NUMBER(10) NOT NULL,
  학점 NUMBER(1) NOT NULL,
  학과코드 NUMBER(10) NOT NULL,
  PRIMARY KEY (과목코드),
  FOREIGN KEY (학과코드) REFERENCES 학과(학과코드)
);

-- 분반 TABLE
CREATE TABLE 분반
(
  분반_번호 NUMBER(1) NOT NULL,
  강의시간 VARCHAR2(50) NOT NULL,
  강의실 NUMBER(4) NOT NULL,
  개설연도_및_학기 VARCHAR2(50) NOT NULL,
  직원번호 NUMBER(10) NOT NULL,
  과목코드 NUMBER(10) NOT NULL,
  PRIMARY KEY (분반_번호, 과목코드),         -- 과목코드가 같을 경우 분반으로 구분
  FOREIGN KEY (과목코드) REFERENCES 과목(과목코드)
);

-- 지도 TABLE
CREATE TABLE 지도
(
  시작일자 DATE,
  종료일자 DATE,
  학번 NUMBER(9) NOT NULL,
  직원번호 NUMBER(10) NOT NULL,
  PRIMARY KEY (학번, 직원번호),
  FOREIGN KEY (직원번호) REFERENCES 교수(직원번호),
  UNIQUE (학번)
);

/*
INSERT INTO 학과 (학과코드, 연락처, 건물, 방번호, 단과대학, 학과명)
VALUES (1234, '051-629-0000', 'A13', 2208, '정보융합대학', '컴퓨터공학과');
INSERT INTO 학과 (학과코드, 연락처, 건물, 방번호, 단과대학, 학과명)
VALUES (2, '02-987-6543', 'A15', 202, '경영대학', '경영학과');


INSERT INTO 학생 (학번, 이름, 주민등록번호, 주소, 연락처, 학년, 학과코드, 직원번호)
VALUES (201911608, '김지환', '000529-3900000', '부산시 남구', '010-7553-6092', 3, 1234);
INSERT INTO 학생 (학번, 이름, 주민등록번호, 주소, 연락처, 학년, 학과코드)
VALUES (201900000, '홍길동', '000111-2900000', '부산시 펭구', '010-0000-0000', 3, 1234);
INSERT INTO 학생 (학번, 이름, 주민등록번호, 주소, 연락처, 학년, 학과코드)
VALUES (201911609, '이지은', '001122-4000000', '부산시 에구', '010-9876-5432', 2, 2);


INSERT INTO 교수 (직원번호, 이름, 주민등록번호, 주소, 연락처, 학과코드) 
VALUES (10001, '송하주', '750611-1000000', '부산시 남구', '010-1111-2222', 1234);
INSERT INTO 교수 (직원번호, 이름, 주민등록번호, 주소, 연락처, 학과코드) 
VALUES (10002, '김태국', '810000-1000000', '부산시 남구', '010-3333-4444', 1234);
INSERT INTO 교수 (직원번호, 이름, 주민등록번호, 주소, 연락처, 학과코드)
VALUES (10003, '오말숙', '850000-2000000', '부산시 호구', '010-5555-6666', 2);


INSERT INTO 과목 (과목명, 과목코드, 학점, 학과코드) 
VALUES ('자료구조', 101, 3, 1234);
INSERT INTO 과목 (과목명, 과목코드, 학점, 학과코드) 
VALUES ('마케팅', 201, 3, 2);

INSERT INTO 분반 (분반_번호, 강의시간, 강의실, 개설연도_및_학기, 직원번호, 과목코드) 
VALUES (1, '월 13:00-15:00', 409, '2023-1학기', 10001, 101);
INSERT INTO 분반 (분반_번호, 강의시간, 강의실, 개설연도_및_학기, 직원번호, 과목코드) 
VALUES (2, '수 09:00-11:00', 2208, '2023-1학기', 10002, 101);
INSERT INTO 분반 (분반_번호, 강의시간, 강의실, 개설연도_및_학기, 직원번호, 과목코드) 
VALUES (1, '월 14:00-15:00', 333, '2023-1학기', 10003, 201);

INSERT INTO 지도 (시작일자, 종료일자, 학번, 직원번호) 
VALUES ('2023-01-01', '2023-06-30', 201900000, 10001);
INSERT INTO 지도 (시작일자, 종료일자, 학번, 직원번호) 
VALUES ('2023-03-01', '2023-08-31', 201911608, 10002);
INSERT INTO 지도 (시작일자, 종료일자, 학번, 직원번호) 
VALUES ('2023-03-01', '2023-08-31', 201911609,10002);

select * from 학과;
select * from 학생;
select * from 교수;
select * from 과목;
select * from 분반;
select * from 지도;

select * from 교수
where 직원번호 = (
    select 직원번호 from 지도 
    natural join 학생
    where 이름 = '김지환'
);

select count(*) 
as 자료구조_분반_수
from 분반
where 과목코드 = (
    select 과목코드 
    from 과목
    where 과목명 = '자료구조'
);
*/