package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderItemBean;

import java.util.List;

public interface B2bOrderItemAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderItemBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderItemBean(B2bOrderItemBean b2bOrderItem);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderItem��
     * @param b2bOrderItem ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderItemBean b2bOrderItem);

    /**
     * �������ݶ���
     * @param b2bOrderItem ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderItemBean b2bOrderItem);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderItem ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderItemBean(B2bOrderItemBean b2bOrderItem);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderItemBean getB2bOrderItemBean(String bdId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderItemBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String bdId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderItemBean trimStringValue(B2bOrderItemBean b2bOrderItem);

    public List<B2bOrderItemBean> queryB2bOrderItemBean
	(B2bOrderItemBean bean, Long startPos, Long num);

    public List<B2bOrderItemBean> queryB2bOrderItemBean
	(B2bOrderItemBean bean);

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
