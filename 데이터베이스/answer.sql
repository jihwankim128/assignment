-- 201911608 ����ȯ
-- �����ͺ��̽� ����
-- ��� ���� : ������ ������

-- 1��
select max(quantity * unit_price) as �ִ��ֹ��ݾ�
from (order_items natural join orders)
natural join customers
where customers.name = 'Raytheon';

-- 2��
select product_id as ��ǰ��ȣ,
product_name as ��ǰ��,
quantity as ��ǰ����,
unit_price as �ܰ�
from ((customers c natural join orders o)
natural join order_items)
natural join products
where c.name = 'Raytheon'
and o.order_date = '17/01/02';

-- 3�� 
select product_id as ��ǰID,
product_name as ��ǰ��
from ((customers natural join orders)
natural join order_items)
natural join products
where name = 'Raytheon'
and quantity >= 100;

-- 4��
select count(*) as Asiaâ����
from ((regions natural join countries)
natural join locations)
natural join warehouses
where region_name = 'Asia';

--5��
select product_id, count(product_id)
from (((regions natural join countries)
natural join locations)
natural join warehouses)
natural join inventories
where region_name = 'Asia'
group by product_id
order by product_id asc;