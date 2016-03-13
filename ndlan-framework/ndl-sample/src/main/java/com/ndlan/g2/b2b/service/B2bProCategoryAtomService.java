package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProCategoryBean;

import java.util.List;

public interface B2bProCategoryAtomService {
    /**
     * �������ݶ���
     * @param b2bProCategoryBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bProCategoryBean(B2bProCategoryBean b2bProCategory);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bProCategory��
     * @param b2bProCategory ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bProCategoryBean b2bProCategory);

    /**
     * �������ݶ���
     * @param b2bProCategory ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bProCategoryBean b2bProCategory);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bProCategory ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bProCategoryBean(B2bProCategoryBean b2bProCategory);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bProCategoryBean getB2bProCategoryBean(String categoryId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bProCategoryBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String categoryId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bProCategoryBean trimStringValue(B2bProCategoryBean b2bProCategory);

    public List<B2bProCategoryBean> queryB2bProCategoryBean
	(B2bProCategoryBean bean, Long startPos, Long num);

    public List<B2bProCategoryBean> queryB2bProCategoryBean
	(B2bProCategoryBean bean);

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
