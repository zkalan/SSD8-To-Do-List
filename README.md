# SSD8-To-Do-List
SSD8实验六
用户注册（检测是否存在）
//成功：Register success 注册成功！
//失败：Register failure注册失败！
//用户名已存在：Username alearly exists 用户名已存在，请重试...
public String register(String name,String password);
//向user表添加数据
//根据name查询是否其存在





//登陆成功返回为真
//登陆失败返回为假
public boolean login(String name,String password);
//首先验证用户名是否存在
//验证密码是否正确



// 序号 | 开始时间 | 结束时间 |
// 		|          |          |
// 概述：######################
//#############################
//################
public String showProject(String name,String password);
//根据用户名查询用户id
//根据用户id查询project表内的所有满足条件project_creator_id = id的project


添加项目（开始时间、结束时间、描述）
//失败：Add project failure 添加项目失败！
//已存在：Project alearly exists 同名项目已存在，请重试...
//成功：Add project success 添加项目成功！
public String addProject(String name,String password,String summary,String start_time,String end_time);
//根据用户名获得用户id
//验证是否存在同名project
//验证时间
//向project表插入数据



查询项目（指定时间段内的项目，列出开始时间、结束时间、标签）
//查询结果为空...
//满足条件的项目有：
// 序号 | 开始时间 | 结束时间 |
// 		|          |          |
// 概述：######################
//#############################
//################
public String searchProjectByTime(String name,String password,String left_time,String right_time);
//根据name查询id
//根据时间和id查询project表




删除项目（通过项目ID）
//删除成功！
//删除失败！
//项目不存在，请重试...
public String deleteProjectByID(String name,String password,String project_id);
//查询project表判断其是否存在
//删除project表中的数据


清空项目（删除所有项目）
//清除成功！
//清除失败！
public String clearProject(String name,String password);
//根据name获得用户id
//根据id删除project内满足条件的数据
