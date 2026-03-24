class Solution {
    // 최적의 결과를 저장하기 위한 필드예요.
    private int maxSubscribers = 0;
    private int maxRevenue = 0;
    // 문제에서 제시한 4가지 할인율이에요.
    private final int[] discountRates = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        // 각 이모티콘의 할인율을 담을 배열을 준비해요.
        int[] currentDiscounts = new int[emoticons.length];
        
        // 0번 이모티콘부터 할인율을 정하기 시작해요.
        findBestPromotion(0, currentDiscounts, users, emoticons);
        
        // 최종적으로 계산된 가입자 수와 매출액을 반환해요.
        return new int[]{maxSubscribers, maxRevenue};
    }

    private void findBestPromotion(int index, int[] currentDiscounts, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인율이 결정되었다면, 해당 조건에서의 시뮬레이션을 수행해요.
        if (index == emoticons.length) {
            updateGlobalResult(currentDiscounts, users, emoticons);
            return;
        }

        // 현재 순서의 이모티콘에 대해 10, 20, 30, 40% 할인율을 하나씩 적용해보는 과정이에요.
        for (int rate : discountRates) {
            currentDiscounts[index] = rate;
            findBestPromotion(index + 1, currentDiscounts, users, emoticons);
        }
    }

    private void updateGlobalResult(int[] currentDiscounts, int[][] users, int[] emoticons) {
        int currentSubscribers = 0;
        int currentRevenue = 0;

        for (int[] user : users) {
            int userThresholdRate = user[0];
            int userThresholdPrice = user[1];
            int sumSpent = 0;

            // 사용자의 기준보다 높게 할인하는 이모티콘만 장바구니
            for (int i = 0; i < emoticons.length; i++) {
                if (currentDiscounts[i] >= userThresholdRate) {
                    sumSpent += emoticons[i] * (100 - currentDiscounts[i]) / 100;
                }
            }

            // 장바구니 금액이 기준을 넘으면 플러스 서비스에 가입하고, 아니면 매출액
            if (sumSpent >= userThresholdPrice) {
                currentSubscribers++;
            } else {
                currentRevenue += sumSpent;
            }
        }

        // 1순위 가입자 수, 2순위 매출액 기준으로 전역 변수를 갱신
        if (currentSubscribers > maxSubscribers) {
            maxSubscribers = currentSubscribers;
            maxRevenue = currentRevenue;
        } else if (currentSubscribers == maxSubscribers) {
            maxRevenue = Math.max(maxRevenue, currentRevenue);
        }
    }
}