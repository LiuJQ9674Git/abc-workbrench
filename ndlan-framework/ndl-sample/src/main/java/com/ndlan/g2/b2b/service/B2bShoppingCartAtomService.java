package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartBean;

import java.util.List;

public interface B2bShoppingCartAtomService {
    /**
     * �������ݶ���
     * @param b2bShoppingCartBean ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveB2bShoppingCartBean(B2bShoppingCartBean b2bShoppingCart);

    /**
     * �������ݶ��󣬲���ȡ����id��b2bShoppingCart��
     * @param b2bShoppingCart ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveAndGetId(B2bShoppingCartBean b2bShoppingCart);

    /**
     * �������ݶ���
     * @param b2bShoppingCart ���ݶ���
     * @return �ɹ�������¼��
     */
    public int update(B2bShoppingCartBean b2bShoppingCart);

    /**
     * �������¶����ж��߼���������id��Ϊnull��0����Ϊ���棬����Ϊ����
     * @param b2bShoppingCart ���ݶ���
     * @return �ɹ�������¼��
     */
    public int saveOrUpdateB2bShoppingCartBean(B2bShoppingCartBean b2bShoppingCart);

    /**
     * ��ȡһ�����ݶ���
     * @param id ����
     * @return ���ݶ���������ʱ����null
     */
    public B2bShoppingCartBean getB2bShoppingCartBean(String cartId);
    /**
     * ��ȡ�������ݶ���
     * @return �������ݶ���������ʱ���ؿռ�¼��
     */
    public List<B2bShoppingCartBean> getAll();

    /**
    * ��������idɾ������
    * @return �ɹ�������¼��
    */
    public void delete(String cartId);

    /**
     * �������е��ַ������͸������ݿⳤ�Ƚ��м��У���ֹ�����ݹ������µ����ݱ���ʧ��
     * @return ���к�����ݶ���
     */
    public B2bShoppingCartBean trimStringValue(B2bShoppingCartBean b2bShoppingCart);

    public List<B2bShoppingCartBean> queryB2bShoppingCartBean
	(B2bShoppingCartBean bean, Long startPos, Long num);

    public List<B2bShoppingCartBean> queryB2bShoppingCartBean
	(B2bShoppingCartBean bean);

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
