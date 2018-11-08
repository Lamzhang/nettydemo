# nettydemo
nettydemo/server

1、netty编写服务器流程
  （1）构建一对主从线程组
  （2）定义服务器启动类
  （3）为服务器设置Channel
  （4）设置处理从线程池的助手类初始化器
  （5）监听启动和关闭服务器
  
  
2、netty的生命周期
  netty的生命周期主要分为：handler添加、channel注册、channel活跃、channel不活跃、channel移除、handler移除
  
