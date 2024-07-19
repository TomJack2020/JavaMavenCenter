with
    amazon_listing_c as (
        select account_id, seller_sku,sku,open_date, source_name
        from amazon_listing
        where open_date >= '2024-03-01' and open_date < '2024-04-01'
          and source_name in ('GPT-AI4-手刊', 'GPT-AI3-手刊','GPT-AI-手刊','GPT-AI5-手刊','GPT-AI6-手刊','GPT-AI3-智刊','GPT-AI402-手刊','常规刊登','智能刊登')
    ),
--链接数据透视
    listing_p AS (PIVOT amazon_listing_c  USING count(account_id||seller_sku) as listing_month_num group by source_name),
--sku维度透视
    sku_p AS (PIVOT amazon_listing_c  USING count( DISTINCT sku ) as sku_month_num group by source_name),
--订单数据
    a as (
SELECT a.seller_sku, a.sku, a.account_id,a.total_price , a.paytime, b.open_date, a.paymonth ,a.currency ,b.source_name,
    date_diff('day', b.open_date ,a.paytime) as d,
    a.seller_sku || '_' || a.account_id as itemid,
    round(a.total_price * c.rate, 2) as rmb_sale ,
    date_diff('day', f.end_time ,b.open_date) as end_diff
FROM amazon_order a
    left join amazon_listing_c b on a.seller_sku = b.seller_sku and a.account_id = b.account_id
    left join currency c
    on a.paymonth = c.paymonth and a.currency = c.currency
    left join spu_sku_info f on a.sku = f.sku
where a.paytime between '2024-03-01' and '2024-05-01' and d <= 30),
--链接维度动销--分刊登类型
    p_rate_listing AS (
    PIVOT a USING round(SUM(rmb_sale),2) as 'day_sale', COUNT(distinct a.seller_sku || a.account_id ) as 'order_listing_num'
GROUP BY source_name),
-- sku维度动销-- 分刊登类型
    p_rate_sku AS (PIVOT a USING round(SUM(rmb_sale),2) as 'day_sale', COUNT(distinct a.sku) as 'order_sku_num'
GROUP BY source_name),
-- sku维度动销结果
    sku_result as (
SELECT a.source_name,a.sku_month_num,b.order_sku_num, round(b.order_sku_num/a.sku_month_num, 4)  as sku_rate, b.day_sale
FROM sku_p a
    LEFT JOIN p_rate_sku b
on a.source_name = b.source_name
    ),
-- listing维度动销结果
    listing_result as (
select a.source_name,a.listing_month_num,b.order_listing_num, round(b.order_listing_num/a.listing_month_num, 4)  as listing_rate, b.day_sale
from listing_p a
    left join p_rate_listing b
on a.source_name = b.source_name
    )
--数据合并
select a.source_name, a.sku_month_num, a.order_sku_num, concat(round(a.sku_rate * 100,2), '%') as sku_rate,
       b.listing_month_num, b.order_listing_num, concat(round(b.listing_rate * 100,2), '%') as listing_rate,
       concat(round(b.day_sale/10000 , 0), '万') as day_sale
from  sku_result a
          left join listing_result b
                    on a.source_name = b.source_name