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
} from "../styled";
import QRCode from "react-native-qrcode-svg";

interface PropsType {
  // 잔액
  balance: string;
  // ID
  id: string;
  // stack navigation
  navigation: any;
}

// Component _ Home
const Home: React.FC<PropsType> = ({ balance, id, navigation }) => {
  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          {/* QR 코드 정보 */}
          <QRcodeContainer>
            <QRCode value={id} size={190} />
            <QRcodeInfoContainer>
              <QRcodeInfoName>{id}</QRcodeInfoName>
              {/* <QRcodeInfoNum>
                <QRcodeInfoNumText onPress={() => console.log(qrCodeInfo.num)}>
                  {qrCodeInfo.num}
                </QRcodeInfoNumText>
              </QRcodeInfoNum> */}
            </QRcodeInfoContainer>
          </QRcodeContainer>
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="송금하기" navigation={navigation} />
          <BtnBox color="blue" text="QR 스캔" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
