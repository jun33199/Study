-- Create table
create table SFDB.SF_JL_QYSDSJMSBA_15
(
  XH            VARCHAR2(15) not null,
  BASQWSH       VARCHAR2(12) not null,
  JSJDM         VARCHAR2(8),
  BAND          VARCHAR2(4),
  SWJGZZJGDM    VARCHAR2(8) not null,
  TBBLX         CHAR(1) not null,
  GDZCMC_SD     VARCHAR2(30),
  SFNXDYZD_SD   CHAR(1),
  SFTGCZQKSM_SD CHAR(1),
  GDZCYZ_SD     NUMBER(17,2),
  GDZCJSJC_SD   NUMBER(17,2),
  SFGDZDNX_SD   NUMBER(4),
  JSZJZDNX_SD   NUMBER(4),
  ZJQSND_SD     CHAR(4),
  ZJZZND_SD     CHAR(4),
  ZJE_SD        NUMBER(17,2),
  YJTZJNX_SD    NUMBER(4),
  YJTZJE_SD     NUMBER(17,2),
  GDZCMC_JS     VARCHAR2(30),
  SFTGFFSM_JS   CHAR(1),
  GDZCYZ_JS     NUMBER(17,2),
  GDZCJSJC_JS   NUMBER(17,2),
  JSZJFFDM_JS   CHAR(1),
  ZJE_JS        NUMBER(17,2),
  SHBJ          CHAR(1),
  CJR           VARCHAR2(30),
  CJRQ          DATE,
  LRR           VARCHAR2(30),
  LRRQ          DATE
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
comment on table SFDB.SF_JL_QYSDSJMSBA_15
  is '15�̶��ʲ������۾����޻�����۾ɱ�������';
-- Add comments to the columns 
comment on column SFDB.SF_JL_QYSDSJMSBA_15.XH
  is '���';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.BASQWSH
  is '�������������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.JSJDM
  is '���������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.BAND
  is '�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SWJGZZJGDM
  is '˰�������֯��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.TBBLX
  is '������ͣ�0�������۾����ޱ���1�������۾ɱ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCMC_SD
  is '�̶��ʲ����ƣ������۾����ޣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SFNXDYZD_SD
  is '���޶�����ͣ�0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SFTGCZQKSM_SD
  is '�ṩ�������˵����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCYZ_SD
  is '�̶��ʲ�ԭֵ�������۾����ޣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCJSJC_SD
  is '�̶��ʲ���˰�����������۾����ޣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SFGDZDNX_SD
  is '˰���涨�������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.JSZJZDNX_SD
  is '�����۾��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.ZJQSND_SD
  is '�����۾���ʼ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.ZJZZND_SD
  is '�����۾���ֹ���';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.ZJE_SD
  is 'ÿ��ɿ۳����۾ɶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.YJTZJNX_SD
  is '�Ѽ����۾ɵ�����';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.YJTZJE_SD
  is '�Ѽ�����۾ɶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCMC_JS
  is '�̶��ʲ����ƣ������۾ɣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SFTGFFSM_JS
  is '�ṩ����˵����0���ǣ�1����';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCYZ_JS
  is '�̶��ʲ�ԭֵ�������۾ɣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.GDZCJSJC_JS
  is '�̶��ʲ���˰�����������۾ɣ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.JSZJFFDM_JS
  is '�����۾ɵķ������룬0��˫�����ݼ�����1�������ܶ';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.ZJE_JS
  is '���۾ɶ�';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.SHBJ
  is '��˱�ǣ�0��ͨ����1����ͨ��';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.CJR
  is '������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.CJRQ
  is '��������';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.LRR
  is '¼����';
comment on column SFDB.SF_JL_QYSDSJMSBA_15.LRRQ
  is '¼������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFDB.SF_JL_QYSDSJMSBA_15
  add constraint PK_SF_JL_QYSDSJMSBA_15 primary key (XH, BASQWSH)
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
alter table SFDB.SF_JL_QYSDSJMSBA_15
  add constraint FK_JMSBA_15_REF_JMSBAJL foreign key (BASQWSH)
  references SFDB.SF_JL_QYSDSJMSBAJL (BASQWSH);
-- Grant/Revoke object privileges 
grant select, insert, update, delete on SFDB.SF_JL_QYSDSJMSBA_15 to SFDB;--R_SWZG