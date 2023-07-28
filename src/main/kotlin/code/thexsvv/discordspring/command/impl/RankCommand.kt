package code.thexsvv.discordspring.command.impl

import code.thexsvv.discordspring.command.Command
import code.thexsvv.discordspring.command.types.BasicCommand
import code.thexsvv.discordspring.service.UserService
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.user.User
import org.javacord.api.entity.user.UserStatus
import org.springframework.beans.factory.annotation.Autowired
import java.awt.*
import java.awt.geom.Ellipse2D
import java.awt.geom.RoundRectangle2D
import java.awt.image.BufferedImage

@Command
class RankCommand : BasicCommand("rank") {

    @Autowired
    private lateinit var userService: UserService;

    override fun onExecute(label: String, message: Message, user: User, args: Array<String>) {
        val image = execute(user);

        MessageBuilder()
            .setContent(localization.localize("blabla.usage", user.name))
            .addAttachment(image, "card.png")
            .send(message.channel);
        userService.levelUp(user.id, user.name);
    }

    fun execute(user: User): BufferedImage {
        val userEntity = userService.getOrCreateUser(user.id, user.name);
        val img = BufferedImage(460, 150, BufferedImage.TYPE_INT_ARGB);
        val graphics = img.createGraphics();

        graphics.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        graphics.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON
        );
        graphics.stroke = BasicStroke(1f);

        drawRectWithStroke(
            graphics,
            0,
            0,
            460,
            150,
            Color(92 / 255f, 0f, 165 / 255f, 255f / 100f * 20 / 255f),
            Color(114, 0, 204)
        );

        graphics.clip = Ellipse2D.Double(15.0, 15.0, 118.0, 118.0);
        graphics.drawImage(user.avatar.asBufferedImage().get(), 15, 15, 118, 118, null); // Avatar circle
        graphics.clip = null;
        drawCircleStroke(graphics, 15, 15, 118, Color.BLACK); // Avatar stroke

        val statusColor = when (user.status) {
            UserStatus.DO_NOT_DISTURB -> Color.RED
            UserStatus.IDLE -> Color.ORANGE
            UserStatus.INVISIBLE -> Color.DARK_GRAY
            UserStatus.OFFLINE -> Color.DARK_GRAY
            else -> Color.GREEN;
        }
        graphics.color = statusColor;
        graphics.fillArc(108, 108, 24, 24, 0, 360);

        drawString(graphics, user.name, 142, 25, 18, Color.WHITE);
        drawString(graphics, "Rank: ${userEntity.rank}", 142, 55, 13, Color.WHITE);
        drawString(graphics, "Level: ${userEntity.level}/${userEntity.maxLevel}", 142, 74, 13, Color.WHITE);
        drawRectWithStroke(
            graphics,
            142,
            110,
            300,
            16,
            Color(92 / 255f, 0f, 165 / 255f, 255f / 100f * 20 / 255f),
            Color(114, 0, 204)
        );

        graphics.color = Color(92 / 255f, 0f, 165 / 255f, 255f / 100f * 80 / 255f);
        graphics.fill(RoundRectangle2D.Double(142.0, 110.0, 300.0, 16.0, 12.0, 12.0));

        graphics.dispose();

        return img;
    }

    private fun drawRectWithStroke(g: Graphics2D, x: Int, y: Int, width: Int, height: Int, fill: Color, stroke: Color) {
        g.color = fill;
        g.fill(RoundRectangle2D.Double(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble(), 12.0, 12.0));

        g.color = stroke;
        g.draw(
            RoundRectangle2D.Double(
                x.toDouble(),
                y.toDouble(),
                (width - 1).toDouble(),
                (height - 2).toDouble(),
                12.0,
                12.0
            )
        );
    }

    private fun drawCircleStroke(g: Graphics2D, x: Int, y: Int, size: Int, stroke: Color) {
        g.color = stroke;
        g.drawArc(x, y, size, size, 0, 360);
    }

    private fun drawString(g: Graphics2D, text: String, x: Int, y: Int, size: Int, color: Color) {
        g.font = Font("Inter", Font.PLAIN, size);
        val fontMetrics = g.fontMetrics;

        g.color = color;
        g.drawString(text, x, y + fontMetrics.ascent);
    }
}
