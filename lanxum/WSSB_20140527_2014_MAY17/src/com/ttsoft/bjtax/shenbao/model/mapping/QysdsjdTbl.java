package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ekernel.db.or.*;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class QysdsjdTbl extends ORTable
{
    static final String tabName = "SBDB.SB_JL_QYSDSJD";
    public QysdsjdTbl(ORManager manager)
    {
        super(manager, tabName, Qysdsjd.class);

    }

}