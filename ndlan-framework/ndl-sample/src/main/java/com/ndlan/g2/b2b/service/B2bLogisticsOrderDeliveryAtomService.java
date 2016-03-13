package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsOrderDeliveryBean;

import java.util.List;

public interface B2bLogisticsOrderDeliveryAtomService {
    /**
     * �������ݶ���
     * @param b2bLogisticsOrderDeliveryBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bLogisticsOrderDeliveryBean(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bLogisticsOrderDelivery��
     * @param b2bLogisticsOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery);

    /**
     * �������ݶ���
     * @param b2bLogisticsOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bLogisticsOrderDelivery ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bLogisticsOrderDeliveryBean(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bLogisticsOrderDeliveryBean getB2bLogisticsOrderDeliveryBean(String lgstOrdDlvId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bLogisticsOrderDeliveryBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String lgstOrdDlvId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bLogisticsOrderDeliveryBean trimStringValue(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery);

    public List<B2bLogisticsOrderDeliveryBean> queryB2bLogisticsOrderDeliveryBean
	(B2bLogisticsOrderDeliveryBean bean, Long startPos, Long num);

    public List<B2bLogisticsOrderDeliveryBean> queryB2bLogisticsOrderDeliveryBean
	(B2bLogisticsOrderDeliveryBean bean);

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
