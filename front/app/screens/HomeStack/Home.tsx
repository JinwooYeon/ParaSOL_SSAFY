import BalanceBox from "../../components/BalanceBox";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  QRcodeContainer,
  QRcodeInfoContainer,
  QRcodeInfoName,
  QRcodeInfoNum,
  QRcodeInfoNumText,
} from "../styled";
import QRCode from "react-native-qrcode-svg";

interface PropsType {
  balance: string;
  navigation: any;
}

const Home: React.FC<PropsType> = ({ balance, navigation }) => {
  let qrCodeInfo = {
    name: "GS25 싸피점",
    num: "0000-1234-9876",
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <QRcodeContainer>
            <QRCode value={qrCodeInfo.num} size={190} />
            <QRcodeInfoContainer>
              <QRcodeInfoName>{qrCodeInfo.name}</QRcodeInfoName>
              <QRcodeInfoNum>
                <QRcodeInfoNumText onPress={() => console.log(qrCodeInfo.num)}>
                  {qrCodeInfo.num}
                </QRcodeInfoNumText>
              </QRcodeInfoNum>
            </QRcodeInfoContainer>
          </QRcodeContainer>
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="송금하기" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
