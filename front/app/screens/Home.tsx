import BalanceBox from "../components/BalanceBox";
import BtnBox from "../components/BtnBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  ContentFooterContainer,
} from "./styled";

interface PropsType {
  balance: string;
  navigation: any;
}

const Home: React.FC<PropsType> = ({ balance, navigation }) => {
  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer></ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="결제하기" navigation={navigation} />
          <BtnBox color="red" text="초기화" navigation={navigation} />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
