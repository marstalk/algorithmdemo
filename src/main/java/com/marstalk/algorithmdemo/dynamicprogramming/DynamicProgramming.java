package com.marstalk.algorithmdemo.dynamicprogramming;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shanzhonglaosou
 */
public class DynamicProgramming extends DataPrepare{

    /**
     * 动态规划在解决背包问题的时候，最重要的是画出这个表格。
     *  ---------|---1---|---2---|---3---|---4---|
     *  guitar--|
     *  stereo--|
     *  laptop--|
     *  iphone--|
     *
     */
    @Test
    public void test(){
        List<Float> virtualBags = sliceBag();
        int[][] valueGrid = new int[goods.size()][virtualBags.size()];
        //print(valueGrid);
        for (int i = 0; i < goods.size(); i++) {
            for (int j = 0; j < virtualBags.size(); j++) {
                //System.out.println(String.format("cell[%d][%d]", i, j));
                String goodName = goods.get(i);
                float goodWeight = wMap.get(goodName);
                float bagSize = virtualBags.get(j);
                if (goodWeight <= bagSize) {
                    //放得下
                    //1，【上一行】在【当前容量】下的价值：cell[i-1][j]
                    int lastRowMaxValue = valueGrid(valueGrid, i-1, j);
                    //2，【当前行】在【当前容量】下的价值
                    int currentRowMaxValue = getCurrentRowMaxValue(virtualBags, valueGrid, i, j, goodName);
                    //3，取两者最大值。
                    int maxVal = Math.max(lastRowMaxValue, currentRowMaxValue);
                    valueGrid[i][j] = maxVal;
                } else {
                    //放不下，则把上一行最大值放进来。
                    valueGrid[i][j] = valueGrid(valueGrid, i-1, j);
                }
            }
        }

        print(valueGrid, virtualBags);

    }

    private int getCurrentRowMaxValue(List<Float> virtualBags, int[][] valueGrid, int i, int j, String goodName) {
        //2.1 计算背包剩余剩余容量：
        float leftSize = virtualBags.get(j) - wMap.get(goodName);
        //2.2 根据剩余容量找到【上一行】的价值
        //TODO 考虑更加复杂的物品重量分布，比如总重量是4.5，而有一个物品的重量是0.7
        int index = findIndex(leftSize, virtualBags);
        return vMap.get(goodName) + valueGrid(valueGrid, i-1, index);
    }

    private int findIndex(float leftSize, List<Float> virtualBags) {
        for (int i = 0; i < virtualBags.size(); i++) {
            if (virtualBags.get(i) >= leftSize) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据坐标获取对应格子的价值，如果越界，则返回0
     * @param valueGrid
     * @param i
     * @param j
     * @return
     */
    private int valueGrid(int[][] valueGrid, int i, int j) {
        try {
            return valueGrid[i][j];
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    private void print(int[][] valueGrid, List<Float> virtualBags) {
        System.out.println(virtualBags);
        for (int i = 0; i < valueGrid.length; i++) {
            System.out.println();
            for (int j = 0; j < valueGrid[i].length; j++) {
                if (j == 0) {
                    String goodName = goods.get(i);
                    System.out.print(goodName + "(" + vMap.get(goodName) + ", " + wMap.get(goodName) + ")" + "  ");
                }
                System.out.print(valueGrid[i][j] + "  ");
            }
        }
        System.out.println();
    }

    private List<Float> sliceBag() {
        float minimumWeight = 0.0F;
        Optional<Float> first = wMap.values().stream().sorted().findFirst();
        if (first.isPresent()) {
            minimumWeight = first.get();
        }

        List<Float> virtualBags = new ArrayList<>();
        float nextBagSize = minimumWeight;
        do {
            virtualBags.add(nextBagSize);
            nextBagSize += minimumWeight;
        } while (nextBagSize <= BAG_SIZE);
        if (virtualBags.get(virtualBags.size() - 1) < BAG_SIZE) {
            virtualBags.add(BAG_SIZE);
        }

        return virtualBags;
    }

}
