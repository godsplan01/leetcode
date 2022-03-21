package cn.eatboooo.crazy.week01;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2022/3/18 21:02
 *
 * 给定两个非负数组x和hp，长度都是N，再给定一个正数range
 * x有序，x[i]表示i号怪兽在x轴上的位置
 * hp[i]表示i号怪兽的血量
 * 再给定一个正数range，表示如果法师释放技能的范围长度
 * 被打到的每只怪兽损失1点血量。返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 *
 * 思路：线段树
 * 贪心：从最左正数范围开始，放完 AOE 再找最左正数...
 */
public class Code06 {
}
