-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_21
(
  XH         VARCHAR2(15) not null,
  BASQWSH    VARCHAR2(12) not null,
  JSJDM      VARCHAR2(8),
  BAND       VARCHAR2(4),
  SWJGZZJGDM VARCHAR2(8) not null,
  MZQSND     CHAR(4),
  MZZZND     CHAR(4),
  SHBJ       CHAR(1),
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
comment on table SFDB.SF_JL_QYSDSJMSBA_21
  is '21������װ���˲���Աר����Ʒ��ҵ��������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_21.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.MZQSND
  is '������ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.MZZZND
  is '������ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.SHBJ
  is '��˱��,0:ͨ��,1:��ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_21.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_21
  add constraint PK_SF_JL_QYSDSJMSBA_21 primary key (XH)
  using index 
  --tablespace TB_SFDB_NO_PT_DEFAULT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SFDB.SF_JL_QYSDSJMSBA_21
  add constraint UK_SF_JL_QYSDSJMSBA_21 unique (BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_21
  add constraint FK_JMSBA_21_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_21 to SFDB;--R_SWZG