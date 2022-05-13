import BtnBox from "../../components/BtnBox";
import {
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import { Linking, Text } from "react-native";
import styled from "styled-components/native";

// Component _ Service
const Service = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>고객 문의</HeaderText>
    <ContentFooterContainer>
      <ContentContainer>
        <Text
          onPress={() =>
            Linking.openURL("https://lab.ssafy.com/s06-final/S06P31S101")
          }
        >
          싸피 깃 바로가기
        </Text>
      </ContentContainer>
      <FooterContainer>
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

const ContentContainer = styled.View`
  flex: 1;
  margin: auto;
  justify-content: center;
`;

export default Service;
