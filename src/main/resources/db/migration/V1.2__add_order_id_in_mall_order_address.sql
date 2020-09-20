ALTER TABLE `mall_order_address`
ADD COLUMN `order_id` bigint NULL COMMENT '订单号',
ADD UNIQUE INDEX `uniq_order_id`(`order_id`) USING BTREE;
