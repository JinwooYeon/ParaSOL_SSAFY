import BalanceBox from "../components/BalanceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  HeaderText,
} from "./styled";

const Home = () => {
  const balance = "999,999";

  return (
    <LayoutContainer>
      <HeaderContainer>
        <HeaderText>PAY 잔액</HeaderText>
        <BalanceBox num={balance} />
      </HeaderContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer></FooterContainer>
    </LayoutContainer>
  );
};

export default Home;
