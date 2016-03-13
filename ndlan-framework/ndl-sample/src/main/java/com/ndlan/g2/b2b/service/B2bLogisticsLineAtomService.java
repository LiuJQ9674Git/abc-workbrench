package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsLineBean;

import java.util.List;

public interface B2bLogisticsLineAtomService {
    /**
     * �������ݶ���
     * @param b2bLogisticsLineBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bLogisticsLineBean(B2bLogisticsLineBean b2bLogisticsLine);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bLogisticsLine��
     * @param b2bLogisticsLine ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bLogisticsLineBean b2bLogisticsLine);

    /**
     * �������ݶ���
     * @param b2bLogisticsLine ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bLogisticsLineBean b2bLogisticsLine);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bLogisticsLine ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bLogisticsLineBean(B2bLogisticsLineBean b2bLogisticsLine);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bLogisticsLineBean getB2bLogisticsLineBean(String logisticsLineId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bLogisticsLineBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String logisticsLineId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bLogisticsLineBean trimStringValue(B2bLogisticsLineBean b2bLogisticsLine);

    public List<B2bLogisticsLineBean> queryB2bLogisticsLineBean
	(B2bLogisticsLineBean bean, Long startPos, Long num);

    public List<B2bLogisticsLineBean> queryB2bLogisticsLineBean
	(B2bLogisticsLineBean bean);

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
