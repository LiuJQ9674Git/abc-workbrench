package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bDeliveryAddressBean;

import java.util.List;

public interface B2bDeliveryAddressAtomService {
    /**
     * �������ݶ���
     * @param b2bDeliveryAddressBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bDeliveryAddressBean(B2bDeliveryAddressBean b2bDeliveryAddress);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bDeliveryAddress��
     * @param b2bDeliveryAddress ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bDeliveryAddressBean b2bDeliveryAddress);

    /**
     * �������ݶ���
     * @param b2bDeliveryAddress ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bDeliveryAddressBean b2bDeliveryAddress);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bDeliveryAddress ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bDeliveryAddressBean(B2bDeliveryAddressBean b2bDeliveryAddress);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bDeliveryAddressBean getB2bDeliveryAddressBean(String deliveryAddressId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bDeliveryAddressBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String deliveryAddressId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bDeliveryAddressBean trimStringValue(B2bDeliveryAddressBean b2bDeliveryAddress);

    public List<B2bDeliveryAddressBean> queryB2bDeliveryAddressBean
	(B2bDeliveryAddressBean bean, Long startPos, Long num);

    public List<B2bDeliveryAddressBean> queryB2bDeliveryAddressBean
	(B2bDeliveryAddressBean bean);

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
