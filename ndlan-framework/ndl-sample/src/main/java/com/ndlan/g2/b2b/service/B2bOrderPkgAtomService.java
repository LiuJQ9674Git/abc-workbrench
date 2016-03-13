package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderPkgBean;

import java.util.List;

public interface B2bOrderPkgAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderPkgBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderPkgBean(B2bOrderPkgBean b2bOrderPkg);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderPkg��
     * @param b2bOrderPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderPkgBean b2bOrderPkg);

    /**
     * �������ݶ���
     * @param b2bOrderPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderPkgBean b2bOrderPkg);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderPkg ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderPkgBean(B2bOrderPkgBean b2bOrderPkg);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderPkgBean getB2bOrderPkgBean(String orderPkgId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderPkgBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String orderPkgId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderPkgBean trimStringValue(B2bOrderPkgBean b2bOrderPkg);

    public List<B2bOrderPkgBean> queryB2bOrderPkgBean
	(B2bOrderPkgBean bean, Long startPos, Long num);

    public List<B2bOrderPkgBean> queryB2bOrderPkgBean
	(B2bOrderPkgBean bean);

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
