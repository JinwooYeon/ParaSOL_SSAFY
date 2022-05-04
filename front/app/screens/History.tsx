import BalanceBox from "../components/BalanceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  HeaderText,
} from "./styled";

const History = () => {
  const balance = "999,999";

  return (
    <LayoutContainer>
      <HeaderContainer>
        <HeaderText>거래 내역</HeaderText>
        <BalanceBox num={balance} />
      </HeaderContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer></FooterContainer>
    </LayoutContainer>
  );
};

export default History;
