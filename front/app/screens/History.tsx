import BalanceBox from "../components/BalanceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
} from "./styled";

interface PropsType {
  balance: string;
}

const History: React.FC<PropsType> = ({ balance }) => {
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
