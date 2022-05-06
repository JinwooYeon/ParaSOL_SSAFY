import BalanceBox from "../components/BalanceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
} from "./styled";

const History = () => {
  const balance = "999,999";

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="거래 내역" num={balance} />
      </HeaderContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer></FooterContainer>
    </LayoutContainer>
  );
};

export default History;
