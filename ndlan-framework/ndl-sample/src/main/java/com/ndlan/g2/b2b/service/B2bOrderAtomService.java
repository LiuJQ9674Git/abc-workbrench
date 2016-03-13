package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderBean;

import java.util.List;

public interface B2bOrderAtomService {
    /**
     * �������ݶ���
     * @param b2bOrderBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bOrderBean(B2bOrderBean b2bOrder);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bOrder��
     * @param b2bOrder ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bOrderBean b2bOrder);

    /**
     * �������ݶ���
     * @param b2bOrder ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bOrderBean b2bOrder);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bOrder ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bOrderBean(B2bOrderBean b2bOrder);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bOrderBean getB2bOrderBean(String bId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bOrderBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String bId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bOrderBean trimStringValue(B2bOrderBean b2bOrder);

    public List<B2bOrderBean> queryB2bOrderBean
	(B2bOrderBean bean, Long startPos, Long num);

    public List<B2bOrderBean> queryB2bOrderBean
	(B2bOrderBean bean);

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
