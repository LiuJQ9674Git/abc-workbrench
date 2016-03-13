package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryDeliveryBean;

import java.util.List;

public interface B2bInventoryDeliveryAtomService {
    /**
     * �������ݶ���
     * @param b2bInventoryDeliveryBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bInventoryDeliveryBean(B2bInventoryDeliveryBean b2bInventoryDelivery);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bInventoryDelivery��
     * @param b2bInventoryDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bInventoryDeliveryBean b2bInventoryDelivery);

    /**
     * �������ݶ���
     * @param b2bInventoryDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bInventoryDeliveryBean b2bInventoryDelivery);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bInventoryDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bInventoryDeliveryBean(B2bInventoryDeliveryBean b2bInventoryDelivery);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bInventoryDeliveryBean getB2bInventoryDeliveryBean(String invDeliveryId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bInventoryDeliveryBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String invDeliveryId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bInventoryDeliveryBean trimStringValue(B2bInventoryDeliveryBean b2bInventoryDelivery);

    public List<B2bInventoryDeliveryBean> queryB2bInventoryDeliveryBean
	(B2bInventoryDeliveryBean bean, Long startPos, Long num);

    public List<B2bInventoryDeliveryBean> queryB2bInventoryDeliveryBean
	(B2bInventoryDeliveryBean bean);

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
