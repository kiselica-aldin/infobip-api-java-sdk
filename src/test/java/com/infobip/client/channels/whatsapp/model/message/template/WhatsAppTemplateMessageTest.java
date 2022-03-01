
package com.infobip.client.channels.whatsapp.model.message.template;

import static org.junit.Assert.assertEquals;
import com.infobip.client.channels.whatsapp.model.common.Language;
import com.infobip.client.common.ModelValidator;
import com.infobip.client.common.Serde;
import jakarta.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class WhatsAppTemplateMessageTest {
    @Test
    void testWhatsAppTemplateMessageSerialization_withRequiredParameters() throws Exception {
        WhatsAppTemplateMessage whatsAppMessage =
                getWhatsAppTemplateMessageWithRequiredParameters();
        String json = Serde.INSTANCE.getObjectMapper().writeValueAsString(whatsAppMessage);
        // System.out.println(whatsAppMessage);
        // System.out.println(json + "\n");
    }

    @Test
    void testWhatsAppTemplateMessageSerialization_withAllParameters() throws Exception {
        WhatsAppTemplateMessage whatsAppMessage = getWhatsAppTemplateMessageWithAllParameters();
        String json = Serde.INSTANCE.getObjectMapper().writeValueAsString(whatsAppMessage);
        // System.out.println(whatsAppMessage);
        // System.out.println(json + "\n");
    }

    @Test
    void validateWhatsAppTemplateMessage_withRequiredParameters() {
        Set<ConstraintViolation<WhatsAppTemplateMessage>> constraintViolations =
                ModelValidator.INSTANCE.getValidator()
                        .validate(getWhatsAppTemplateMessageWithRequiredParameters());
        assertEquals(0, constraintViolations.size());
    }

    @Test
    void validateWhatsAppTemplateMessage_withAllParameters() {
        Set<ConstraintViolation<WhatsAppTemplateMessage>> constraintViolations =
                ModelValidator.INSTANCE.getValidator()
                        .validate(getWhatsAppTemplateMessageWithAllParameters());
        assertEquals(0, constraintViolations.size());
    }

    public static WhatsAppTemplateMessage getWhatsAppTemplateMessageWithRequiredParameters() {
        return new WhatsAppTemplateMessage(Arrays.asList(new TemplateMessage("441134960000",
                "441134960001",
                new Content("template_name",
                        new TemplateData(new Body(
                                Arrays.asList("Placeholder Value 1", "Placeholder Value 2"))),
                        Language.EN_GB))));
    }

    public static WhatsAppTemplateMessage getWhatsAppTemplateMessageWithAllParameters() {
        return new WhatsAppTemplateMessage(
                Arrays.asList(
                        new TemplateMessage("441134960000", "441134960001", new Content(
                                "template_name",
                                new TemplateData(new Body(Arrays.asList("Placeholder Value 1",
                                        "Placeholder Value 2")))
                                                .header(new TextHeader("placeholder value"))
                                                .buttons(Arrays.asList(
                                                        new QuickReplyButton("button 1"),
                                                        new QuickReplyButton("button 2"))),
                                Language.EN_GB))
                                        .smsFailover(new SmsFailover("441134960000", "Some text"))
                                        .messageId("a28dd97c-1ffb-4fcf-99f1-0b557ed381da")
                                        .callbackData("Callback data")
                                        .notifyUrl("https://www.example.com/whatsapp"),
                        new TemplateMessage("441134960000", "441134960001",
                                new Content("template_name_2", new TemplateData(new Body(Arrays
                                        .asList("Placeholder Value 3", "Placeholder Value 4"))),
                                        Language.EN_US)))).bulkId("1");
    }
}
