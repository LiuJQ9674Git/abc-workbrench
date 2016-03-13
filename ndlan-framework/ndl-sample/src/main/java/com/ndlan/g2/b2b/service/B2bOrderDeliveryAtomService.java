package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderDeliveryBean;

import java.util.List;

public interface B2bOrderDeliveryAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderDeliveryBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderDeliveryBean(B2bOrderDeliveryBean b2bOrderDelivery);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderDelivery��
     * @param b2bOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderDeliveryBean b2bOrderDelivery);

    /**
     * �������ݶ���
     * @param b2bOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderDeliveryBean b2bOrderDelivery);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderDeliveryBean(B2bOrderDeliveryBean b2bOrderDelivery);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderDeliveryBean getB2bOrderDeliveryBean(String orderDeliveryId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderDeliveryBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String orderDeliveryId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderDeliveryBean trimStringValue(B2bOrderDeliveryBean b2bOrderDelivery);

    public List<B2bOrderDeliveryBean> queryB2bOrderDeliveryBean
	(B2bOrderDeliveryBean bean, Long startPos, Long num);

    public List<B2bOrderDeliveryBean> queryB2bOrderDeliveryBean
	(B2bOrderDeliveryBean bean);

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
