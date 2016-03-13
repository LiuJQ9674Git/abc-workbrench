package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartDetailBean;

import java.util.List;

public interface B2bShoppingCartDetailAtomService {
    /**
     * �������ݶ���
     * @param b2bShoppingCartDetailBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bShoppingCartDetailBean(B2bShoppingCartDetailBean b2bShoppingCartDetail);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bShoppingCartDetail��
     * @param b2bShoppingCartDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bShoppingCartDetailBean b2bShoppingCartDetail);

    /**
     * �������ݶ���
     * @param b2bShoppingCartDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bShoppingCartDetailBean b2bShoppingCartDetail);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bShoppingCartDetail ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bShoppingCartDetailBean(B2bShoppingCartDetailBean b2bShoppingCartDetail);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bShoppingCartDetailBean getB2bShoppingCartDetailBean(String cartItemId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bShoppingCartDetailBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String cartItemId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bShoppingCartDetailBean trimStringValue(B2bShoppingCartDetailBean b2bShoppingCartDetail);

    public List<B2bShoppingCartDetailBean> queryB2bShoppingCartDetailBean
	(B2bShoppingCartDetailBean bean, Long startPos, Long num);

    public List<B2bShoppingCartDetailBean> queryB2bShoppingCartDetailBean
	(B2bShoppingCartDetailBean bean);

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
