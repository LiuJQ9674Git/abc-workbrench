package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductClientBean;

import java.util.List;

public interface B2bProductClientAtomService {
    /**
     * �������ݶ���
     * @param b2bProductClientBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bProductClientBean(B2bProductClientBean b2bProductClient);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bProductClient��
     * @param b2bProductClient ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bProductClientBean b2bProductClient);

    /**
     * �������ݶ���
     * @param b2bProductClient ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bProductClientBean b2bProductClient);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bProductClient ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bProductClientBean(B2bProductClientBean b2bProductClient);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bProductClientBean getB2bProductClientBean(String proId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bProductClientBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String proId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bProductClientBean trimStringValue(B2bProductClientBean b2bProductClient);

    public List<B2bProductClientBean> queryB2bProductClientBean
	(B2bProductClientBean bean, Long startPos, Long num);

    public List<B2bProductClientBean> queryB2bProductClientBean
	(B2bProductClientBean bean);

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
