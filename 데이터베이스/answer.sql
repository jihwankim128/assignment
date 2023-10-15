-- 201911608 김지환
-- 데이터베이스 과제
-- 담당 교수 : 송하주 교수님

-- 1번
select max(quantity * unit_price) as 최대주문금액
from (order_items natural join orders)
natural join customers
where customers.name = 'Raytheon';

-- 2번
select product_id as 제품번호,
product_name as 제품명,
quantity as 제품수량,
unit_price as 단가
from ((customers c natural join orders o)
natural join order_items)
natural join products
where c.name = 'Raytheon'
and o.order_date = '17/01/02';

-- 3번 
select product_id as 제품ID,
product_name as 제품명
from ((customers natural join orders)
natural join order_items)
natural join products
where name = 'Raytheon'
and quantity >= 100;

-- 4번
select count(*) as Asia창고개수
from ((regions natural join countries)
natural join locations)
natural join warehouses
where region_name = 'Asia';

--5번
select product_id, count(product_id)
from (((regions natural join countries)
natural join locations)
natural join warehouses)
natural join inventories
where region_name = 'Asia'
group by product_id
order by product_id asc;