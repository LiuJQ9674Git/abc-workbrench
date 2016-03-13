package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierCardAccountBean;

import java.util.List;

public interface B2bSupplierCardAccountAtomService {
    /**
     * �������ݶ���
     * @param b2bSupplierCardAccountBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bSupplierCardAccountBean(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bSupplierCardAccount��
     * @param b2bSupplierCardAccount ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
     * �������ݶ���
     * @param b2bSupplierCardAccount ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bSupplierCardAccount ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bSupplierCardAccountBean(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bSupplierCardAccountBean getB2bSupplierCardAccountBean(String supCardAcntId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bSupplierCardAccountBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String supCardAcntId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bSupplierCardAccountBean trimStringValue(B2bSupplierCardAccountBean b2bSupplierCardAccount);

    public List<B2bSupplierCardAccountBean> queryB2bSupplierCardAccountBean
	(B2bSupplierCardAccountBean bean, Long startPos, Long num);

    public List<B2bSupplierCardAccountBean> queryB2bSupplierCardAccountBean
	(B2bSupplierCardAccountBean bean);

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
