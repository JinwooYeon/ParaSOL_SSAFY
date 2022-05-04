import { Balance, BalanceContainer } from "../screens/styled";

const BalanceBox = ({ num }: any) => {
  return (
    <BalanceContainer>
      <Balance>{num}</Balance>
      <Balance>원</Balance>
    </BalanceContainer>
  );
};

export default BalanceBox;
