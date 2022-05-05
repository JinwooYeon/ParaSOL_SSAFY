import { Balance, BoxContainer, BalanceTextContainer } from "../screens/styled";

const BalanceBox = ({ num }: any) => {
  return (
    <BoxContainer>
      <Balance>잔액</Balance>
      <BalanceTextContainer>
        <Balance>{num}</Balance>
        <Balance>원</Balance>
      </BalanceTextContainer>
    </BoxContainer>
  );
};

export default BalanceBox;
