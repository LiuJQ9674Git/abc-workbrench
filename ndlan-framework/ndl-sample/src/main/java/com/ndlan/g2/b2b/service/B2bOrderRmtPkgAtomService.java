package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtPkgBean;

import java.util.List;

public interface B2bOrderRmtPkgAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderRmtPkgBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderRmtPkgBean(B2bOrderRmtPkgBean b2bOrderRmtPkg);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderRmtPkg��
     * @param b2bOrderRmtPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderRmtPkgBean b2bOrderRmtPkg);

    /**
     * �������ݶ���
     * @param b2bOrderRmtPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderRmtPkgBean b2bOrderRmtPkg);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderRmtPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderRmtPkgBean(B2bOrderRmtPkgBean b2bOrderRmtPkg);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderRmtPkgBean getB2bOrderRmtPkgBean(String orderRmtPkgId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderRmtPkgBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String orderRmtPkgId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderRmtPkgBean trimStringValue(B2bOrderRmtPkgBean b2bOrderRmtPkg);

    public List<B2bOrderRmtPkgBean> queryB2bOrderRmtPkgBean
	(B2bOrderRmtPkgBean bean, Long startPos, Long num);

    public List<B2bOrderRmtPkgBean> queryB2bOrderRmtPkgBean
	(B2bOrderRmtPkgBean bean);

    /**
     * ����sql������ɾ��
     * @param whereSql where�ؼ��ֺ����ɾ�����������磺id = ?
     * @param params �����еĲ�����������Ҫ��"?"��������һ��
     * @return �ɹ�ɾ���ļ�¼��
     */
    public int deleteByWhereSql(String whereSql, Object[] params);

     /**
    * ��������
    * @param sql �������
    * @param args ����
    * @return �ɹ����µļ�¼��
    */
    public int update(String sql, Object... args);

}
