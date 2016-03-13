package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryHeadBean;

import java.util.List;

public interface B2bPriceCategoryHeadAtomService {
    /**
     * �������ݶ���
     * @param b2bPriceCategoryHeadBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bPriceCategoryHeadBean(B2bPriceCategoryHeadBean b2bPriceCategoryHead);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bPriceCategoryHead��
     * @param b2bPriceCategoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bPriceCategoryHeadBean b2bPriceCategoryHead);

    /**
     * �������ݶ���
     * @param b2bPriceCategoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bPriceCategoryHeadBean b2bPriceCategoryHead);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bPriceCategoryHead ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bPriceCategoryHeadBean(B2bPriceCategoryHeadBean b2bPriceCategoryHead);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bPriceCategoryHeadBean getB2bPriceCategoryHeadBean(String priCatHeadId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bPriceCategoryHeadBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String priCatHeadId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bPriceCategoryHeadBean trimStringValue(B2bPriceCategoryHeadBean b2bPriceCategoryHead);

    public List<B2bPriceCategoryHeadBean> queryB2bPriceCategoryHeadBean
	(B2bPriceCategoryHeadBean bean, Long startPos, Long num);

    public List<B2bPriceCategoryHeadBean> queryB2bPriceCategoryHeadBean
	(B2bPriceCategoryHeadBean bean);

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
