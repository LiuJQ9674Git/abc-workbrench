package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtDetailBean;

import java.util.List;

public interface B2bOrderRmtDetailAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderRmtDetailBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderRmtDetailBean(B2bOrderRmtDetailBean b2bOrderRmtDetail);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrderRmtDetail��
     * @param b2bOrderRmtDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderRmtDetailBean b2bOrderRmtDetail);

    /**
     * �������ݶ���
     * @param b2bOrderRmtDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderRmtDetailBean b2bOrderRmtDetail);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrderRmtDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderRmtDetailBean(B2bOrderRmtDetailBean b2bOrderRmtDetail);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderRmtDetailBean getB2bOrderRmtDetailBean(String orderRmtDetailId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderRmtDetailBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String orderRmtDetailId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderRmtDetailBean trimStringValue(B2bOrderRmtDetailBean b2bOrderRmtDetail);

    public List<B2bOrderRmtDetailBean> queryB2bOrderRmtDetailBean
	(B2bOrderRmtDetailBean bean, Long startPos, Long num);

    public List<B2bOrderRmtDetailBean> queryB2bOrderRmtDetailBean
	(B2bOrderRmtDetailBean bean);

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
