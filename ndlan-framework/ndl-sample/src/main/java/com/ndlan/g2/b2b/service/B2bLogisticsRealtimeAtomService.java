package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsRealtimeBean;

import java.util.List;

public interface B2bLogisticsRealtimeAtomService {
    /**
     * �������ݶ���
     * @param b2bLogisticsRealtimeBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bLogisticsRealtimeBean(B2bLogisticsRealtimeBean b2bLogisticsRealtime);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bLogisticsRealtime��
     * @param b2bLogisticsRealtime ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bLogisticsRealtimeBean b2bLogisticsRealtime);

    /**
     * �������ݶ���
     * @param b2bLogisticsRealtime ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bLogisticsRealtimeBean b2bLogisticsRealtime);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bLogisticsRealtime ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bLogisticsRealtimeBean(B2bLogisticsRealtimeBean b2bLogisticsRealtime);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bLogisticsRealtimeBean getB2bLogisticsRealtimeBean(String logisticsRealtimeId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bLogisticsRealtimeBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String logisticsRealtimeId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bLogisticsRealtimeBean trimStringValue(B2bLogisticsRealtimeBean b2bLogisticsRealtime);

    public List<B2bLogisticsRealtimeBean> queryB2bLogisticsRealtimeBean
	(B2bLogisticsRealtimeBean bean, Long startPos, Long num);

    public List<B2bLogisticsRealtimeBean> queryB2bLogisticsRealtimeBean
	(B2bLogisticsRealtimeBean bean);

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
