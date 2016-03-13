package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductBean;

import java.util.List;

public interface B2bProductAtomService {
    /**
     * �������ݶ���
     * @param b2bProductBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bProductBean(B2bProductBean b2bProduct);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bProduct��
     * @param b2bProduct ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bProductBean b2bProduct);

    /**
     * �������ݶ���
     * @param b2bProduct ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bProductBean b2bProduct);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bProduct ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bProductBean(B2bProductBean b2bProduct);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bProductBean getB2bProductBean(String proId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bProductBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String proId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bProductBean trimStringValue(B2bProductBean b2bProduct);

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean bean, Long startPos, Long num);

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean bean);

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
