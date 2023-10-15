-- �а� TABLE
CREATE TABLE �а�
(
  �а��ڵ� NUMBER(10) NOT NULL,         
  ����ó VARCHAR2(20) NOT NULL,        
  �ǹ� NVARCHAR2(10) NOT NULL,             -- ex) ���İ� or A15
  ���ȣ NUMBER(4) NOT NULL,               -- ex) 409
  �ܰ����� NVARCHAR2(50) NOT NULL,         -- ex) �������մ���
  �а��� NVARCHAR2(50) NOT NULL,           -- ex) ��ǻ�Ͱ��к� 
  PRIMARY KEY (�а��ڵ�),
  UNIQUE (�а���)
);

-- �л� TABLE, (�������, ������ Derived attribute) 
CREATE TABLE �л�
(
  �й� NUMBER(9) NOT NULL,                -- ex) 201911608
  �̸� NVARCHAR2(50) NOT NULL,            -- ex) ����ȯ
  �ֹε�Ϲ�ȣ VARCHAR2(14) NOT NULL,      -- ex) 000529-3000000
  �ּ� NVARCHAR2(100) NOT NULL,           -- ex) �λ걤���� ���� ...
  ����ó VARCHAR2(20) NOT NULL,           -- ex) 010-0000-0000
  �г� NUMBER(1) NOT NULL,                -- ex) 3
  �а��ڵ� NUMBER(10) NOT NULL,            
  PRIMARY KEY (�й�),
  FOREIGN KEY (�а��ڵ�) REFERENCES �а�(�а��ڵ�),
  FOREIGN KEY (������ȣ) REFERENCES ����(������ȣ),
  UNIQUE (�ֹε�Ϲ�ȣ)
);

-- Derived Attribute (����, �������)
ALTER TABLE �л� ADD (
    -- GENERATED ALWAYS AS == �Ļ��÷� ����
    ���� NVARCHAR2(4) GENERATED ALWAYS AS (
        CASE WHEN 
            SUBSTR(�ֹε�Ϲ�ȣ, 8, 1) IN ('1', '3') 
            THEN '��' 
            ELSE '��' 
        END
    ),
    ������� DATE GENERATED ALWAYS AS (TO_DATE(SUBSTR(�ֹε�Ϲ�ȣ, 1, 6), 'YYMMDD'))
);

-- ���� TABLE
CREATE TABLE ����
(
  ������ȣ NUMBER(10) NOT NULL,
  �̸� NVARCHAR2(50) NOT NULL,
  �ֹε�Ϲ�ȣ VARCHAR2(14) NOT NULL,
  �ּ� NVARCHAR2(100) NOT NULL,
  ����ó VARCHAR2(20) NOT NULL,
  �а��ڵ� NUMBER(10) NOT NULL,
  PRIMARY KEY (������ȣ),
  FOREIGN KEY (�а��ڵ�) REFERENCES �а�(�а��ڵ�),
  UNIQUE (�ֹε�Ϲ�ȣ)
);

-- ���� TABLE
CREATE TABLE ����
(
  ����� NVARCHAR2(50) NOT NULL,
  �����ڵ� NUMBER(10) NOT NULL,
  ���� NUMBER(1) NOT NULL,
  �а��ڵ� NUMBER(10) NOT NULL,
  PRIMARY KEY (�����ڵ�),
  FOREIGN KEY (�а��ڵ�) REFERENCES �а�(�а��ڵ�)
);

-- �й� TABLE
CREATE TABLE �й�
(
  �й�_��ȣ NUMBER(1) NOT NULL,
  ���ǽð� VARCHAR2(50) NOT NULL,
  ���ǽ� NUMBER(4) NOT NULL,
  ��������_��_�б� VARCHAR2(50) NOT NULL,
  ������ȣ NUMBER(10) NOT NULL,
  �����ڵ� NUMBER(10) NOT NULL,
  PRIMARY KEY (�й�_��ȣ, �����ڵ�),         -- �����ڵ尡 ���� ��� �й����� ����
  FOREIGN KEY (�����ڵ�) REFERENCES ����(�����ڵ�)
);

-- ���� TABLE
CREATE TABLE ����
(
  �������� DATE,
  �������� DATE,
  �й� NUMBER(9) NOT NULL,
  ������ȣ NUMBER(10) NOT NULL,
  PRIMARY KEY (�й�, ������ȣ),
  FOREIGN KEY (������ȣ) REFERENCES ����(������ȣ),
  UNIQUE (�й�)
);

/*
INSERT INTO �а� (�а��ڵ�, ����ó, �ǹ�, ���ȣ, �ܰ�����, �а���)
VALUES (1234, '051-629-0000', 'A13', 2208, '�������մ���', '��ǻ�Ͱ��а�');
INSERT INTO �а� (�а��ڵ�, ����ó, �ǹ�, ���ȣ, �ܰ�����, �а���)
VALUES (2, '02-987-6543', 'A15', 202, '�濵����', '�濵�а�');


INSERT INTO �л� (�й�, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �г�, �а��ڵ�, ������ȣ)
VALUES (201911608, '����ȯ', '000529-3900000', '�λ�� ����', '010-7553-6092', 3, 1234);
INSERT INTO �л� (�й�, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �г�, �а��ڵ�)
VALUES (201900000, 'ȫ�浿', '000111-2900000', '�λ�� �뱸', '010-0000-0000', 3, 1234);
INSERT INTO �л� (�й�, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �г�, �а��ڵ�)
VALUES (201911609, '������', '001122-4000000', '�λ�� ����', '010-9876-5432', 2, 2);


INSERT INTO ���� (������ȣ, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �а��ڵ�) 
VALUES (10001, '������', '750611-1000000', '�λ�� ����', '010-1111-2222', 1234);
INSERT INTO ���� (������ȣ, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �а��ڵ�) 
VALUES (10002, '���±�', '810000-1000000', '�λ�� ����', '010-3333-4444', 1234);
INSERT INTO ���� (������ȣ, �̸�, �ֹε�Ϲ�ȣ, �ּ�, ����ó, �а��ڵ�)
VALUES (10003, '������', '850000-2000000', '�λ�� ȣ��', '010-5555-6666', 2);


INSERT INTO ���� (�����, �����ڵ�, ����, �а��ڵ�) 
VALUES ('�ڷᱸ��', 101, 3, 1234);
INSERT INTO ���� (�����, �����ڵ�, ����, �а��ڵ�) 
VALUES ('������', 201, 3, 2);

INSERT INTO �й� (�й�_��ȣ, ���ǽð�, ���ǽ�, ��������_��_�б�, ������ȣ, �����ڵ�) 
VALUES (1, '�� 13:00-15:00', 409, '2023-1�б�', 10001, 101);
INSERT INTO �й� (�й�_��ȣ, ���ǽð�, ���ǽ�, ��������_��_�б�, ������ȣ, �����ڵ�) 
VALUES (2, '�� 09:00-11:00', 2208, '2023-1�б�', 10002, 101);
INSERT INTO �й� (�й�_��ȣ, ���ǽð�, ���ǽ�, ��������_��_�б�, ������ȣ, �����ڵ�) 
VALUES (1, '�� 14:00-15:00', 333, '2023-1�б�', 10003, 201);

INSERT INTO ���� (��������, ��������, �й�, ������ȣ) 
VALUES ('2023-01-01', '2023-06-30', 201900000, 10001);
INSERT INTO ���� (��������, ��������, �й�, ������ȣ) 
VALUES ('2023-03-01', '2023-08-31', 201911608, 10002);
INSERT INTO ���� (��������, ��������, �й�, ������ȣ) 
VALUES ('2023-03-01', '2023-08-31', 201911609,10002);

select * from �а�;
select * from �л�;
select * from ����;
select * from ����;
select * from �й�;
select * from ����;

select * from ����
where ������ȣ = (
    select ������ȣ from ���� 
    natural join �л�
    where �̸� = '����ȯ'
);

select count(*) 
as �ڷᱸ��_�й�_��
from �й�
where �����ڵ� = (
    select �����ڵ� 
    from ����
    where ����� = '�ڷᱸ��'
);
*/