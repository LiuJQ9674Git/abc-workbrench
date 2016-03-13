package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailBean;

import java.util.List;

public interface B2bPriceCategoryDetailAtomService {
    /**
     * �������ݶ���
     * @param b2bPriceCategoryDetailBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bPriceCategoryDetailBean(B2bPriceCategoryDetailBean b2bPriceCategoryDetail);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bPriceCategoryDetail��
     * @param b2bPriceCategoryDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bPriceCategoryDetailBean b2bPriceCategoryDetail);

    /**
     * �������ݶ���
     * @param b2bPriceCategoryDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bPriceCategoryDetailBean b2bPriceCategoryDetail);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bPriceCategoryDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bPriceCategoryDetailBean(B2bPriceCategoryDetailBean b2bPriceCategoryDetail);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bPriceCategoryDetailBean getB2bPriceCategoryDetailBean(String priCatLineId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bPriceCategoryDetailBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String priCatLineId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bPriceCategoryDetailBean trimStringValue(B2bPriceCategoryDetailBean b2bPriceCategoryDetail);

    public List<B2bPriceCategoryDetailBean> queryB2bPriceCategoryDetailBean
	(B2bPriceCategoryDetailBean bean, Long startPos, Long num);

    public List<B2bPriceCategoryDetailBean> queryB2bPriceCategoryDetailBean
	(B2bPriceCategoryDetailBean bean);

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
