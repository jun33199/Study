-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_06
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  JNJSXMLXDM CHAR(2) not null,
  ZLMC       VARCHAR2(100),
  DYBSRND    CHAR(4),
  SFYHSQKSM  CHAR(1),
  QTZL       VARCHAR2(2000),
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  JZQSND     CHAR(4),
  JZZZND     CHAR(4),
  SHBJ       CHAR(1),
  ZCBA       CHAR(1) default '1',
  CJR        VARCHAR2(30),
  CJRQ       DATE,
  LRR        VARCHAR2(30),
  LRRQ       DATE
)
--tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SFDB.SF_JL_QYSDSJMSBA_06
  is '06���·��������Ļ������������ܽ�ˮ��Ŀ������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_06.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.JNJSXMLXDM
  is '�������������ܽ�ˮ��Ŀ���ʹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.ZLMC
  is '�йز����϶�����Ŀ֤����������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.DYBSRND
  is '��һ������ȡ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.SFYHSQKSM
  is '��Ŀ���ú������������0���У�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.QTZL
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.JZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.JZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.ZCBA
  is '�ٴα��� 0:�ٴα�����1��δ�ٴα���';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_06.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_06
  add constraint PK_SF_JL_QYSDSJMSBA_06 primary key (XH, BASQWSH)
  using index 
 -- tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_06
  add constraint FK_JMSBA_06_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_06 to SFDB;--R_SWZG