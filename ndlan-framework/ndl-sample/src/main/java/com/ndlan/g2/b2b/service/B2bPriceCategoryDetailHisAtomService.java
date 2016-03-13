package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailHisBean;

import java.util.List;

public interface B2bPriceCategoryDetailHisAtomService {
    /**
     * �������ݶ���
     * @param b2bPriceCategoryDetailHisBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bPriceCategoryDetailHisBean(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bPriceCategoryDetailHis��
     * @param b2bPriceCategoryDetailHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis);

    /**
     * �������ݶ���
     * @param b2bPriceCategoryDetailHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bPriceCategoryDetailHis ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bPriceCategoryDetailHisBean(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bPriceCategoryDetailHisBean getB2bPriceCategoryDetailHisBean(String priCtyDtlhisId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bPriceCategoryDetailHisBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String priCtyDtlhisId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bPriceCategoryDetailHisBean trimStringValue(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis);

    public List<B2bPriceCategoryDetailHisBean> queryB2bPriceCategoryDetailHisBean
	(B2bPriceCategoryDetailHisBean bean, Long startPos, Long num);

    public List<B2bPriceCategoryDetailHisBean> queryB2bPriceCategoryDetailHisBean
	(B2bPriceCategoryDetailHisBean bean);

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
