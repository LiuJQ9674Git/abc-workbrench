package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierAgencyBean;

import java.util.List;

public interface B2bSupplierAgencyAtomService {
    /**
     * �������ݶ���
     * @param b2bSupplierAgencyBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bSupplierAgencyBean(B2bSupplierAgencyBean b2bSupplierAgency);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bSupplierAgency��
     * @param b2bSupplierAgency ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bSupplierAgencyBean b2bSupplierAgency);

    /**
     * �������ݶ���
     * @param b2bSupplierAgency ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bSupplierAgencyBean b2bSupplierAgency);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bSupplierAgency ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bSupplierAgencyBean(B2bSupplierAgencyBean b2bSupplierAgency);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bSupplierAgencyBean getB2bSupplierAgencyBean(String supplierAgencyId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bSupplierAgencyBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String supplierAgencyId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bSupplierAgencyBean trimStringValue(B2bSupplierAgencyBean b2bSupplierAgency);

    public List<B2bSupplierAgencyBean> queryB2bSupplierAgencyBean
	(B2bSupplierAgencyBean bean, Long startPos, Long num);

    public List<B2bSupplierAgencyBean> queryB2bSupplierAgencyBean
	(B2bSupplierAgencyBean bean);

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
